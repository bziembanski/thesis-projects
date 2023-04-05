import 'package:flutter/material.dart';
import 'package:flutter_example/screens/first_screen.dart';
import 'package:flutter_example/screens/second_screen.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  static const List<Widget> _screens = [
    FirstScreen(),
    SecondScreen(),
  ];

  final _pageController = PageController(initialPage: 0, keepPage: true);

  int _selectedScreen = 0;

  void _onItemChanged(int index) {
    setState(() {
      _selectedScreen = index;
    });
    _pageController.animateToPage(
      index,
      duration: const Duration(milliseconds: 200),
      curve: Curves.easeInOut,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Flutter Example"),
        backgroundColor: Theme.of(context).colorScheme.secondaryContainer,
      ),
      key: const Key("main_screen"),
      body: PageView(
        physics: const NeverScrollableScrollPhysics(),
        controller: _pageController,
        children: _screens,
      ),
      bottomNavigationBar: BottomNavigationBar(
        backgroundColor: Theme.of(context).colorScheme.background,
        currentIndex: _selectedScreen,
        onTap: _onItemChanged,
        items: const [
          BottomNavigationBarItem(
            icon: Icon(Icons.one_k),
            label: "First Item",
            tooltip: "First Item",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.two_k),
            label: "Second Item",
            tooltip: "Second Item",
          ),
        ],
      ),
    );
  }
}
