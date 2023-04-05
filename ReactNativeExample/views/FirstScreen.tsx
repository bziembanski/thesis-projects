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

function FirstScreen(): JSX.Element {
    const isDarkMode = useColorScheme() === 'dark';


    return (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
            <Text>First Screen</Text>
        </View>
    );
}

export default FirstScreen;