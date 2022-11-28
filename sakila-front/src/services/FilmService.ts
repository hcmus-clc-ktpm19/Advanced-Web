import axios from 'axios';
import { FilmDto } from '../models/model';

export const FilmService = {
  getFilms: async (): Promise<FilmDto[]> => {
    return (await axios.get('http://localhost:8080/api/v1/films')).data;
  }
};
