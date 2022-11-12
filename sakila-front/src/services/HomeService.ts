import axios from 'axios';
import { ActorDto, CategoryDto, FilmDto } from '../models/model';

export const HomeService = {
  getActors: async (): Promise<ActorDto[]> => {
    return (await axios.get<ActorDto[]>('http://localhost:8080/api/v1/actors')).data;
  },

  getFilms: async (): Promise<FilmDto[]> => {
    return (await axios.get<FilmDto[]>('http://localhost:8080/api/v1/films')).data;
  },

  getCategories: async (): Promise<CategoryDto[]> => {
    return (await axios.get<CategoryDto[]>('http://localhost:8080/api/v1/categories')).data;
  }
};