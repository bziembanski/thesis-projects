import 'package:dio/dio.dart';
import 'package:flutter_example/data/person.dart';

class RemoteRepository {
  static final dio = Dio();

  RemoteRepository() {
    dio.options
      ..baseUrl = 'https://swapi.dev/api/'
      ..connectTimeout = const Duration(seconds: 20)
      ..receiveTimeout = const Duration(seconds: 20);
  }

  Future<List<Person>> fetchAll() async {
    final response = await dio.get("people");
    return ((response.data["results"] as List<dynamic>)
            .cast<Map<String, dynamic>>())
        .map((e) => Person.fromJson(e))
        .toList();
  }

  Future<Person> fetchPerson(String id) async {
    final response = await dio.get("people/$id");
    return Person.fromJson(response.data as Map<String, dynamic>);
  }
}
