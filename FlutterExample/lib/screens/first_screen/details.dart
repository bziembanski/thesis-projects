import 'package:flutter/material.dart';
import 'package:flutter_example/data/person.dart';
import 'package:flutter_example/repository/remote_repository.dart';

class Details extends StatefulWidget {
  const Details({super.key});

  @override
  State<Details> createState() => _DetailsState();
}

class _DetailsState extends State<Details> {
  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      final id = ModalRoute.of(context)!.settings.arguments as String;
      fetchPerson(id);
    });
  }

  void fetchPerson(String id) {
    RemoteRepository().fetchPerson(id).then(
      (value) {
        setState(() {
          _person = value;
        });
      },
    );
  }

  Person? _person;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(_person?.name ?? "Loading"),
        backgroundColor: Theme.of(context).colorScheme.secondaryContainer,
      ),
      key: const Key("main_screen"),
      body: _person == null
          ? const Center(child: CircularProgressIndicator())
          : _buildDetails(context),
    );
  }
  Widget _buildDetails(BuildContext context){
    return Column();
  }
}
