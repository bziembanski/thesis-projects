import { useTheme } from '@react-navigation/native';
import React, { useEffect, useRef } from 'react';
import type { PropsWithChildren } from 'react';
import { FlatList, VirtualizedList } from 'react-native';
import {
    Image,
    SafeAreaView,
    ScrollView,
    StatusBar,
    StyleSheet,
    Text,
    useColorScheme,
    View,
} from 'react-native';
import { Emboss, ScreenBlendColor } from 'react-native-image-filter-kit';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import { Colors } from 'react-native/Libraries/NewAppScreen';
import { Int32 } from 'react-native/Libraries/Types/CodegenTypes';

function randomIntFromInterval(min: Int32, max: Int32): Int32 {
    return Math.floor(Math.random() * (max - min + 1) + min);
}

function randomRadius(): Int32 {
    return randomIntFromInterval(0, 128);
}

function randomColor(): string {
    const color = "#" + Math.random().toString(16).substr(-6);
    return color
}

type ItemData = {
    key: string;
    color: string;
    radius: Int32
}

function ThirdScreen(): JSX.Element {
    const isDarkMode = useColorScheme() === 'dark';
    const theme = useTheme();
    const items: ItemData[] = Array.from(Array(1000).keys()).map((it) => ({
        color: randomColor(),
        radius: randomRadius(),
        key: "itemData" + it.toString()
    }));
    
   
    return (
        <FlatList<ItemData>
            initialNumToRender={100}
            data={items}
            renderItem={
                (it) => <View style={{
                    flex: 1,
                    padding: 16,
                }}>
                    <View style={{
                        elevation: 16,
                        borderRadius: it.item.radius,
                        overflow: "hidden",
                        aspectRatio:2.4929041190723433714087919695396,
                        width:"100%"
                    }}>
                        <ScreenBlendColor
                            srcColor={it.item.color}
                            dstImage={
                                <Image
                                    source={require('../img/image.jpg')}
                                    style={{ width: '100%', height: '100%', }}
                                />
                            }
                        />
                    </View>
                </View>
            }
        />
    );
}

const styles = StyleSheet.create({
    textStyle: {
        fontSize: 16
    }
});

export default ThirdScreen;