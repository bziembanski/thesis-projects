import { useTheme } from '@react-navigation/native';
import React from 'react';
import type { PropsWithChildren } from 'react';
import { FlatList } from 'react-native';
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
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import { Colors } from 'react-native/Libraries/NewAppScreen';

function SecondScreen(): JSX.Element {
    const isDarkMode = useColorScheme() === 'dark';
    const theme = useTheme();

    const _desc =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla accumsan sodales nisl, in commodo ex lacinia iaculis. Donec feugiat lacinia malesuada. Nam semper sapien quis erat commodo, id rutrum arcu gravida. Sed id pharetra augue, sed eleifend metus. Donec semper tristique eros at venenatis. Mauris nec felis scelerisque, ultrices odio id, maximus magna. Nam ultrices, neque quis aliquet volutpat, tellus libero dapibus ligula, a feugiat turpis leo sit amet nisl. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. ";


    return (
        <View style={{
            flex: 1
        }}>
            <ScrollView centerContent={true} contentContainerStyle={{ alignItems: 'center' }}>
                <View style={{
                    flex: 1,
                    paddingHorizontal: 128,
                    paddingTop: 32,
                    paddingBottom: 16,
                    borderColor: "black"
                }}>
                    <View style={{
                        width: '100%',
                        height: undefined,
                        aspectRatio: 1,
                        elevation: 8,
                        borderRadius: 500
                    }}>
                        <Image source={require('../img/avatar.png')} style={{ width: '100%', height: '100%' }} />
                    </View>
                </View>

                <View style={{
                    width: "100%",
                    padding: 32,
                    flex: 1,
                    justifyContent: "center",
                }}>
                    <View style={{
                        width: "100%",
                        flexDirection: "row",
                        padding: 16,
                        borderRadius: 24,
                        elevation: 8,
                        justifyContent: "space-around",
                        backgroundColor: theme.colors.card,
                    }}>
                        <Text style={styles.textStyle}>Name</Text>
                        <Text style={styles.textStyle}>Surname</Text>
                    </View>

                </View>

                <View style={{
                    width: "100%",
                    padding: 32,
                    paddingTop: 0,
                    flex: 1,
                    justifyContent: "center",
                }}>
                    <View style={{
                        width: "100%",
                        flexDirection: "row",
                        padding: 24,
                        borderRadius: 24,
                        elevation: 8,
                        justifyContent: "space-around",
                        backgroundColor: theme.colors.card,
                    }}>
                        <Text style={[
                            styles.textStyle,
                            {
                                fontStyle: "italic",
                                lineHeight: 24,
                                textAlign: "justify",
                            }
                        ]}>{_desc}</Text>
                    </View>
                </View>

                <View style={{
                    width: "100%",
                    paddingHorizontal: 32,
                    paddingBottom: 32,
                    flex: 1,
                    justifyContent: "center",
                }}>
                    <View style={{
                        width: "100%",
                        flexDirection: "row",
                        justifyContent: "space-around",
                    }}>
                        <MaterialIcons name={"account-circle"} color={theme.colors.primary} size={48} />
                        <MaterialIcons name={"camera"} color={theme.colors.primary} size={48} />
                        <MaterialIcons name={"calendar-month"} color={theme.colors.primary} size={48} />
                    </View>

                </View>

            </ScrollView>
        </View>
    );
}

const styles = StyleSheet.create({
    textStyle: {
        fontSize: 16
    }
});

export default SecondScreen;