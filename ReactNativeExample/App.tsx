import React from 'react';
import {
  useColorScheme,
} from 'react-native';
import { DarkTheme, DefaultTheme, NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import SecondScreen from './views/SecondScreen';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import { FirstScreenStack } from './views/FirstScreen';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import ThirdScreen from './views/ThirdScreen';
import performance, { PerformanceObserver } from 'react-native-performance';

const Tab = createBottomTabNavigator();

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  // new PerformanceObserver((list, observer) => {
  //   if (list.getEntries().find((entry) => entry.name === "runJsBundleEnd")) {
  //     console.log(performance.measure('runJsBundle', 'runJsBundleStart', 'contentAppeared'));
  //   }
  // }).observe({ type: "react-native-mark", buffered: true });

  return (
    <NavigationContainer theme={isDarkMode ? DarkTheme : DefaultTheme}>
      <Tab.Navigator screenOptions={{}} backBehavior='initialRoute'>
        <Tab.Screen name="First Item" component={FirstScreenStack} options={{
          headerShown: false,
          tabBarIcon: (props) => (<MaterialIcons name={"1k"} color={props.color} size={props.size} />)
        }} />
        <Tab.Screen name="Second Item" component={SecondScreen} options={{
          headerTitle: "Second Screen",
          headerStyle: { elevation: 4, shadowColor: "black" },
          tabBarIcon: (props) => (<MaterialIcons name={"2k"} color={props.color} size={props.size} />)
        }} />
        <Tab.Screen name="Third Item" component={ThirdScreen} options={{
          headerTitle: "Third Screen",
          headerStyle: { elevation: 4, shadowColor: "black" },
          tabBarIcon: (props) => (<MaterialIcons name={"3k"} color={props.color} size={props.size} />)
        }} />
      </Tab.Navigator>
    </NavigationContainer>
  );
}

export default App;
