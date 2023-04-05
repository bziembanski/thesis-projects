import React from 'react';
import type { PropsWithChildren } from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';
import { DarkTheme, DefaultTheme, NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import FirstScreen from './views/FirstScreen';
import SecondScreen from './views/SecondScreen';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';

const Tab = createBottomTabNavigator();

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';


  return (
    <NavigationContainer theme={isDarkMode ? DarkTheme : DefaultTheme}>
      <Tab.Navigator screenOptions={{ headerTitle: "React-Native Example" }}>
        <Tab.Screen name="First Item" component={FirstScreen} options={{
          tabBarIcon: (props) => (<MaterialIcons name={"1k"} color={props.color} size={props.size}/>)
        }} />
        <Tab.Screen name="Second Item" component={SecondScreen} options={{
          tabBarIcon: (props) => (<MaterialIcons name={"2k"} color={props.color} size={props.size} />)
        }} />
      </Tab.Navigator>
    </NavigationContainer>
  );
}

export default App;
