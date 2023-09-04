import 'package:flutter/material.dart';
import 'package:flutter_example/main_screen.dart';
import 'package:flutter_example/screens/first_screen/details.dart';



void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Example',
      home: const MainScreen(),
      routes: {
        '/details': (context) => const Details(),
      },
      theme: ThemeData.light(useMaterial3: true),
      darkTheme: ThemeData.dark(useMaterial3: true),
      themeMode: ThemeMode.system,
    );
  }
}
