import { Int32 } from "react-native/Libraries/Types/CodegenTypes";
import { PeopleResult, Person } from "../data/person";
import axios from "axios";


export class RemoteRepository{
    instance = axios.create({
        baseURL: 'https://swapi.dev/api/',
    });
    async fetchAll(): Promise<Person[]>{
        let response =  await this.instance.get("people");
        let result: PeopleResult = response.data;
        return result.results;
    }

    async fetchPerson(personId: Int32): Promise<Person>{
        let response =  await this.instance.get(`people/${personId}`);
        let result: Person = response.data;
        return result;
    }
}