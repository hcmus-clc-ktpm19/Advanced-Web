import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, ButtonGroup, Container, ListGroup } from 'react-bootstrap';
import React, { useState } from 'react';
import axios from 'axios';
import { ActorDto, CategoryDto, FilmDto } from '../models/model';

const HomeView = (): JSX.Element => {
  const handleOnclick = async (e: React.MouseEvent<HTMLElement>) => {
    const htmlElement = e.target as HTMLElement;
    setActors([]);
    setCategories([]);
    setFilms([]);
    await fetchData(htmlElement.id);
  };

  const fetchData = async (apiName: string) => {
    switch (apiName) {
      case 'actors':
        setActors((await axios.get<ActorDto[]>(`http://localhost:8080/api/v1/${apiName}`)).data);
        break;
      case 'films':
        setFilms((await axios.get<FilmDto[]>(`http://localhost:8080/api/v1/${apiName}`)).data);
        break;
      case 'categories':
        setCategories(
          (await axios.get<CategoryDto[]>(`http://localhost:8080/api/v1/${apiName}`)).data
        );
        break;
    }
  };

  const [actors, setActors] = useState<ActorDto[]>([]);
  const [films, setFilms] = useState<FilmDto[]>([]);
  const [categories, setCategories] = useState<CategoryDto[]>([]);

  return (
    <Container className="px-4 py-5">
      <h2 className="pb-2 border-bottom fw-bold">Home</h2>
      <div className="d-flex flex-column justify-content-around py-5">
        <ButtonGroup aria-label="Basic example" onClick={handleOnclick}>
          <Button id="actors" variant="outline-primary fw-bold">
            Actors
          </Button>
          <Button id="films" variant="outline-primary fw-bold">
            Films
          </Button>
          <Button id="categories" variant="outline-primary fw-bold">
            Categories
          </Button>
        </ButtonGroup>

        <div className="g-4 mt-5 py-2">
          <ListGroup className="text-center">
            {actors.length > 0 &&
              actors.map((actor: ActorDto) => (
                <ListGroup.Item>{`${actor.firstName} ${actor.lastName}`}</ListGroup.Item>
              ))}
            {films.length > 0 &&
              films.map((film: FilmDto) => <ListGroup.Item>{film.title}</ListGroup.Item>)}
            {categories.length > 0 &&
              categories.map((category: CategoryDto) => (
                <ListGroup.Item>{category.name}</ListGroup.Item>
              ))}
          </ListGroup>
        </div>
      </div>
    </Container>
  );
};

export default HomeView;
