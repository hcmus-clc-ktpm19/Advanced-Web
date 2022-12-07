/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.0.1157 on 2022-12-07 21:59:44.

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

export interface CategoryDto extends Serializable {
    categoryId: number;
    name: string;
    lastUpdate: Date;
}

export interface OutputMessageDto {
    message: string;
    time: string;
}

export interface Serializable {
}
