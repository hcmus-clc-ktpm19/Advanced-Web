import axios from 'axios';
import { ActorDto, CategoryDto, FilmDto } from '../models/model';

export const HomeService = {
  getActors: async (): Promise<ActorDto[]> => {
    return (
      await axios.get<ActorDto[]>('http://localhost:8080/api/v1/actors', {
        headers: {
          Authorization: localStorage.getItem('token') || ''
        }
      })
    ).data;
  },

  getFilms: async (): Promise<FilmDto[]> => {
    return (
      await axios.get<FilmDto[]>('http://localhost:8080/api/v1/films', {
        headers: {
          Authorization: localStorage.getItem('token') || ''
        }
      })
    ).data;
  },

  getCategories: async (): Promise<CategoryDto[]> => {
    return (
      await axios.get<CategoryDto[]>('http://localhost:8080/api/v1/categories', {
        headers: {
          Authorization: localStorage.getItem('token') || ''
        }
      })
    ).data;
  }
};
