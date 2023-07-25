import 'package:flutter/material.dart';
import 'package:flutter_example/data/person.dart';
import 'package:flutter_example/repository/remote_repository.dart';

class FirstScreen extends StatefulWidget {
  const FirstScreen({Key? key}) : super(key: key);

  @override
  State<FirstScreen> createState() => _FirstScreenState();
}

class _FirstScreenState extends State<FirstScreen>
    with AutomaticKeepAliveClientMixin {

  @override
  bool get wantKeepAlive => true;

  @override
  void initState() {
    super.initState();
    fetchPeople();
  }

  void fetchPeople() {
    RemoteRepository().fetchAll().then((value) {
      if (mounted) {
        setState(() {
          _people = value;
        });
      }
    });
  }

  List<Person>? _people;

  @override
  Widget build(BuildContext context) {
    super.build(context);
    if (_people == null) {
      return const Center(
        child: CircularProgressIndicator(),
      );
    } else {
      return ListView.separated(
        itemCount: _people!.length,
        separatorBuilder: (context, index) {
          return Container(
            margin: const EdgeInsets.symmetric(horizontal: 8),
            height: 1,
            color: Theme.of(context).colorScheme.surfaceVariant,
          );
        },
        itemBuilder: (context, index) {
          final person = _people![index];
          final id = person.url.split("/").lastWhere((s) => s.isNotEmpty);
          return ListTile(
            key: Key(id),
            title: Text(person.name),
            subtitle: Text(person.gender),
            onTap: () {
              Navigator.pushNamed(context, "/details", arguments: id);
            },
          );
        },
      );
    }
  }
}
