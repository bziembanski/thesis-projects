import { NativeStackScreenProps } from "@react-navigation/native-stack";
import * as Progress from 'react-native-progress';
import { useEffect, useState } from "react";
import { FlatList, StyleSheet, Text, TouchableNativeFeedback, View, useColorScheme } from "react-native";
import { RemoteRepository } from "../repository/remote_repository";
import { Person } from "../data/person";
import { FirstScreenStackParamList } from "./FirstScreen";
import { useTheme } from "@react-navigation/native";

type ContentListType = {
    key: string;
    value: string;
};

type Props = NativeStackScreenProps<FirstScreenStackParamList, 'DetailsScreen'>;
export function DetailsScreen({ navigation, route }: Props): JSX.Element {
    const isDarkMode = useColorScheme() === 'dark';

    const theme = useTheme();

    const [person, setPerson] = useState<Person | null>(null);

    async function getPerson() {
        const person = await new RemoteRepository().fetchPerson(route.params.personId);
        navigation.setOptions({ headerTitle: person.name })
        setPerson(person);
    };

    useEffect(() => {
        navigation.getParent()?.setOptions({
            tabBarStyle: {
                display: "none"
            }
        });
        getPerson();

        return () => navigation.getParent()?.setOptions({
            tabBarStyle: undefined
        });
    }, [])



    const items: ContentListType[] = person ? [
        { key: "Name: ", value: person!.name },
        { key: "Gender: ", value: person!.gender },
        { key: "Height: ", value: person!.height },
        { key: "Mass: ", value: person!.mass },
        { key: "Skin color: ", value: person!.skin_color },
        { key: "Hair color: ", value: person!.hair_color },
        { key: "Eye color: ", value: person!.eye_color },
        { key: "Birth year: ", value: person!.birth_year },
    ] : [];

    return (
        <View style={{ flex: 1, justifyContent: "center", alignItems: items.length == 0 ? "center" : undefined }}>
            {
                items.length == 0
                    ? <Progress.CircleSnail size={40} indeterminate={true} thickness={3} />
                    : <FlatList<ContentListType>
                        data={items}
                        renderItem={(row) =>
                            <View
                                style={{
                                    flex: 1,
                                    padding: 16,
                                    flexDirection: 'row',
                                    alignItems: 'center',
                                    justifyContent: 'space-between',
                                    backgroundColor: theme.colors.background,
                                    borderBottomWidth: 0.5,
                                    borderBottomColor: theme.colors.border,
                                }}>
                                <Text style={[styles.textStyle, { color: theme.colors.text, fontWeight: "bold" }]}>
                                    {row.item.key}
                                </Text>
                                <Text style={[styles.textStyle, { color: theme.colors.text }]}>
                                    {row.item.value}
                                </Text>
                            </View>
                        }
                    >
                    </FlatList>
            }
        </View>
    );
}
const styles = StyleSheet.create({
    textStyle: {
        fontSize: 16
    }
});