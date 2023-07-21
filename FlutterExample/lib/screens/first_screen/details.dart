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
        if (mounted) {
          setState(() {
            _person = value;
          });
        }
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
          ? Container(
              alignment: Alignment.center,
              color: Theme.of(context).colorScheme.surfaceVariant,
              child: const CircularProgressIndicator(),
            )
          : _buildDetails(context),
    );
  }

  Widget _buildInfoRow(String key, String value) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 16),
      decoration: BoxDecoration(
        border: Border(
          bottom: BorderSide(
            color: Theme.of(context).colorScheme.surfaceVariant,
            width: 1,
          ),
        ),
        color: Theme.of(context).colorScheme.surface,
      ),
      child: Row(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            "$key:",
            style: const TextStyle(fontSize: 17, fontWeight: FontWeight.w800),
          ),
          Text(value, style: const TextStyle(fontSize: 16))
        ],
      ),
    );
  }

  Widget _buildDetails(BuildContext context) {
    return Container(
      color: Theme.of(context).colorScheme.surfaceVariant,
      child: ListView(
        children: [
          _buildInfoRow("Name", _person!.name),
          _buildInfoRow("Gender", _person!.gender),
          _buildInfoRow("Height", "${_person!.height}cm"),
          _buildInfoRow("Mass", "${_person!.mass}kg"),
          _buildInfoRow("Skin color", _person!.skinColor),
          _buildInfoRow("Hair color", _person!.hairColor),
          _buildInfoRow("Eye color", _person!.eyeColor),
          _buildInfoRow("Birth year", _person!.birthYear),
        ],
      ),
    );
  }
}
