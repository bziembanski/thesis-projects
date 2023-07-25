import React, { useEffect, useState } from 'react';
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

function FirstScreen(): JSX.Element {
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
        <View style={{ flex: 1 }}>
            {
                people.length == 0
                    ? <Progress.CircleSnail indeterminate={true} thickness={3} />
                    : <PeopleList people={people} />
            }
        </View>
    );
}

interface PeopleListPros {
    people: Person[],
}

function PeopleList(props: PeopleListPros): JSX.Element {
    const theme = useTheme();
    return (
        <FlatList<Person>
            style={{ flex: 1 }}
            data={props.people}
            renderItem={
                (person) => <TouchableNativeFeedback>
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

export default FirstScreen;