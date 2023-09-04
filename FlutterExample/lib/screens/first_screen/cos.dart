import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  bool isButtonClicked = false;

  void toggleButton() {
    setState(() {
      isButtonClicked = !isButtonClicked;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Declarative UI Example'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: toggleButton,
          child: Text(
            isButtonClicked ? 'Button Clicked' : 'Click Me',
          ),
        ),
      ),
    );
  }
}
