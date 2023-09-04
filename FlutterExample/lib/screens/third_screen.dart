import 'dart:math';

import 'package:flutter/material.dart';

class ThirdScreen extends StatelessWidget {
  ThirdScreen({super.key});
  final rnd = Random();
  late final scrollController = ScrollController();

  int random(int min, int max) {
    return min + rnd.nextInt(max - min);
  }

  Widget _buildDecoratedBox(
    BuildContext context
  ) {
    return Container(
      clipBehavior: Clip.antiAlias,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(random(0, 128).toDouble()),
        color: Theme.of(context).colorScheme.surfaceVariant,
        boxShadow: [
          BoxShadow(
            color: Theme.of(context).shadowColor.withOpacity(0.2),
            spreadRadius: 4,
            blurRadius: 8,
            offset: const Offset(2, 4),
          ),
        ],
      ),
      margin: const EdgeInsets.all(16),
      child: AspectRatio(
        aspectRatio: 2.4929041190723433714087919695396,
        child: Image.asset(
          "assets/images/image.jpg",
          color:
              Color.fromARGB(255, random(0, 255), random(0, 255), random(0, 255)),
          colorBlendMode: BlendMode.screen,
        ),
      ),
    );
  }
  static bool runOnce = true;
  @override
  Widget build(BuildContext context) {
    if(runOnce){
      WidgetsBinding.instance.addPostFrameCallback((_) {
        scrollController.animateTo(
          scrollController.position.maxScrollExtent,
          duration: const Duration(seconds: 27),
          curve: Curves.linear,
        );
      });
      runOnce = false;
    }

    return ListView.builder(
      controller: scrollController,
      itemCount: 1000,
      itemBuilder: (context, index) {
        return _buildDecoratedBox(context);
      },
    );
  }
}
