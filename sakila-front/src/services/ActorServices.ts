import axios from 'axios';
import { ActorDto } from '../models/model';

export const ActorService = {
  getActors: async (): Promise<ActorDto[]> => {
    return (await axios.get('http://localhost:8080/api/v1/actors')).data;
  }
};
