import 'package:flutter/material.dart';

class SecondScreen extends StatelessWidget {
  const SecondScreen({Key? key}) : super(key: key);

  static const _desc =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla accumsan sodales nisl, in commodo ex lacinia iaculis. Donec feugiat lacinia malesuada. Nam semper sapien quis erat commodo, id rutrum arcu gravida. Sed id pharetra augue, sed eleifend metus. Donec semper tristique eros at venenatis. Mauris nec felis scelerisque, ultrices odio id, maximus magna. Nam ultrices, neque quis aliquet volutpat, tellus libero dapibus ligula, a feugiat turpis leo sit amet nisl. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. ";

  static const textStyle = TextStyle(fontSize: 16);
  static const textStyleItalics = TextStyle(
    fontSize: 16,
    fontStyle: FontStyle.italic,
  );

  Widget _buildAvatar(BuildContext context) {
    return Container(
      clipBehavior: Clip.antiAlias,
      margin: const EdgeInsets.symmetric(horizontal: 128, vertical: 32),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(128),
        boxShadow: [
          BoxShadow(
            color: Theme.of(context).shadowColor.withOpacity(0.2),
            spreadRadius: 8,
            blurRadius: 16,
          ),
        ],
      ),
      child: AspectRatio(
        aspectRatio: 1,
        child: Image.asset("assets/images/avatar.png"),
      ),
    );
  }

  Widget _buildDecoratedBox(
    BuildContext context,
    Widget child, {
    double? padding,
  }) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(32),
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
      margin: const EdgeInsets.symmetric(horizontal: 24),
      padding: EdgeInsets.all(padding ?? 16),
      child: child,
    );
  }

  Widget _buildName(BuildContext context) {
    return _buildDecoratedBox(
      context,
      const Row(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          Text("Name", style: textStyle),
          Text("Surname", style: textStyle),
        ],
      ),
    );
  }

  Widget _buildDesc(BuildContext context) {
    return _buildDecoratedBox(
      context,
      const Text(
        _desc,
        style: textStyleItalics,
        textAlign: TextAlign.justify,
      ),
      padding: 24,
    );
  }

  Widget _buildSocialIcons(BuildContext context) {
    Widget buildSocialIcon(
      IconData icon, {
      Color? color,
      double? size,
    }) {
      return Container(
        margin: const EdgeInsets.only(left: 16, right: 16, bottom: 8),
        child: Icon(
          icon,
          size: size ?? 48,
          color: color ?? Theme.of(context).colorScheme.primary,
        ),
      );
    }

    return Container(
      margin: const EdgeInsets.symmetric(horizontal: 24),
      padding: const EdgeInsets.all(16),
      child: Wrap(
        alignment: WrapAlignment.spaceAround,
        children: [
          buildSocialIcon(Icons.account_circle_rounded),
          buildSocialIcon(Icons.calendar_month_sharp),
          buildSocialIcon(Icons.camera_rounded),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
        _buildAvatar(context),
        _buildName(context),
        const SizedBox(height: 32),
        _buildDesc(context),
        const SizedBox(height: 32),
        _buildSocialIcons(context),
        const SizedBox(height: 32),
      ],
    );
  }
}
