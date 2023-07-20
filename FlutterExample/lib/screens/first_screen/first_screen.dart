import 'package:flutter/material.dart';
import 'package:flutter_example/data/person.dart';
import 'package:flutter_example/repository/remote_repository.dart';

class FirstScreen extends StatefulWidget {
  const FirstScreen({Key? key}) : super(key: key);

  @override
  State<FirstScreen> createState() => _FirstScreenState();
}

class _FirstScreenState extends State<FirstScreen> {
  @override
  void initState() {
    super.initState();
    fetchPeople();
  }

  void fetchPeople() {
    RemoteRepository().fetchAll().then((value) {
      setState(() {
        _people = value;
      });
    });
  }

  List<Person>? _people;
  @override
  Widget build(BuildContext context) {
    if (_people == null) {
      return const Center(
        child: CircularProgressIndicator(),
      );
    } else {

      return ListView.builder(
        itemCount: _people!.length,
        itemBuilder: (context, index) {
          final person = _people![index];
          final id = person.url.split("/").where((s)=>s.isNotEmpty).last;
          return ListTile(
            key: Key(id),
            title: Text(person.name),
            subtitle: Text(person.gender),
            onTap: (){
              Navigator.pushNamed(context, "/details", arguments: id);
            },
          );
        },
      );
    };
  }
}
