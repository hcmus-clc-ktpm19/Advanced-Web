/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.0.1157 on 2022-11-16 22:36:30.

export interface ActorDto {
    id: number;
    firstName: string;
    lastName: string;
}

export interface FilmDto {
    filmId: number;
    title: string;
    description: string;
    releaseYear: number;
    languageId: number;
    originalLanguageId: number;
    rentalDuration: number;
    rentalRate: number;
    length: number;
    replacementCost: number;
    rating: string;
    specialFeatures: string;
}

export interface CategoryDto {
    categoryId: number;
    name: string;
    lastUpdate: Date;
}

export interface UserDto {
    username: string;
    password: string;
}
