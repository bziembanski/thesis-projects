import React, { useEffect, useState } from 'react';
import { NativeStackScreenProps, createNativeStackNavigator } from '@react-navigation/native-stack';
import * as Progress from 'react-native-progress';
import {
    FlatList,
    StyleSheet,
    Text,
    TouchableNativeFeedback,
    useColorScheme,
    View,
} from 'react-native';
import { Person } from '../data/person';
import { RemoteRepository } from '../repository/remote_repository';
import { useTheme } from '@react-navigation/native';
import { Int32 } from 'react-native/Libraries/Types/CodegenTypes';
import { DetailsScreen } from './DetailsScreen';



export type FirstScreenStackParamList = {
    FirstScreen: undefined;
    DetailsScreen: { personId: Int32 };
};

const FirstStack = createNativeStackNavigator<FirstScreenStackParamList>();

export function FirstScreenStack() {
    return (
        <FirstStack.Navigator>
            <FirstStack.Screen name="FirstScreen" component={FirstScreen} options={{ headerTitle: "First Screen" }} />
            <FirstStack.Screen name="DetailsScreen" component={DetailsScreen} options={{ headerTitle: "Loading" }} />
        </FirstStack.Navigator>
    );
}

type Props = NativeStackScreenProps<FirstScreenStackParamList, 'FirstScreen'>;
export function FirstScreen({ navigation }: Props): JSX.Element {
    const isDarkMode = useColorScheme() === 'dark';

    const [people, setPeople] = useState<Person[]>([]);

    async function getPeople() {
        const people = await new RemoteRepository().fetchAll();
        setPeople(people);
    };

    useEffect(() => {
        getPeople();
    }, [])



    return (
        <View style={{ flex: 1, justifyContent: "center", alignItems: people.length == 0 ? "center" : undefined }}>
            {
                people.length == 0
                    ? <Progress.CircleSnail size={40} indeterminate={true} thickness={3} />
                    : <PeopleList
                        people={people}
                        onTap={(person: Person) => { navigation.navigate("DetailsScreen", { personId: parseInt(person.url.split("/").filter(it => it !== "").pop()!) }); }}
                    />
            }
        </View>
    );
}

interface PeopleListPros {
    people: Person[],
    onTap: (person: Person) => void,
}

function PeopleList(props: PeopleListPros): JSX.Element {
    const theme = useTheme();
    return (
        <FlatList<Person>
            data={props.people}
            renderItem={
                (person) => <TouchableNativeFeedback onPress={() => { props.onTap(person.item) }}>

                    <View
                        style={{
                            padding: 16,
                            flexDirection: 'column',
                            alignItems: 'flex-start',
                            alignContent: 'flex-start',
                            backgroundColor: theme.colors.background,
                            borderBottomWidth: 0.5,
                            borderBottomColor: theme.colors.border,
                        }}>
                        <Text style={[styles.textStyle, { color: theme.colors.text }]}>{person.item.name}</Text>
                        <Text style={[styles.textStyle, { color: theme.colors.text }]}>{person.item.gender}</Text>
                    </View>
                </TouchableNativeFeedback>
            }
        />
    );
}

const styles = StyleSheet.create({
    textStyle: {
        fontSize: 16
    }
});