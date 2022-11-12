import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, ListGroup, ToggleButton, ToggleButtonGroup } from 'react-bootstrap';
import React, { useState } from 'react';
import { ActorDto, CategoryDto, FilmDto } from '../../models/model';
import { HomeService } from '../../services/HomeService';

const HomeView = (): JSX.Element => {
  const [actors, setActors] = useState<ActorDto[]>([]);
  const [films, setFilms] = useState<FilmDto[]>([]);
  const [categories, setCategories] = useState<CategoryDto[]>([]);

  const handleOnclick = async (e: React.MouseEvent<HTMLElement>): Promise<void> => {
    const htmlElement = e.target as HTMLElement;
    setActors([]);
    setCategories([]);
    setFilms([]);
    await fetchData(htmlElement.id);
  };

  const fetchData = async (apiName: string): Promise<void> => {
    switch (apiName) {
      case 'actors':
        setActors(await HomeService.getActors());
        break;
      case 'films':
        setFilms(await HomeService.getFilms());
        break;
      case 'categories':
        setCategories(await HomeService.getCategories());
        break;
    }
  };

  return (
    <Container className="px-4 py-5">
      <h2 className="pb-2 border-bottom fw-bold">Home</h2>
      <div className="d-flex flex-column justify-content-around py-5">
        <ToggleButtonGroup
          type="radio"
          name="options"
          aria-label="Basic example"
          onClick={handleOnclick}>
          <ToggleButton id="actors" variant="outline-primary fw-bold" value="actors">
            Actors
          </ToggleButton>
          <ToggleButton id="films" variant="outline-primary fw-bold" value="films">
            Films
          </ToggleButton>
          <ToggleButton id="categories" variant="outline-primary fw-bold" value="categories">
            Categories
          </ToggleButton>
        </ToggleButtonGroup>

        <div className="g-4 mt-5 py-2">
          <ListGroup className="text-center">
            {actors.length > 0 &&
              actors.map((actor: ActorDto) => (
                <ListGroup.Item
                  key={actor.id}>{`${actor.firstName} ${actor.lastName}`}</ListGroup.Item>
              ))}
            {films.length > 0 &&
              films.map((film: FilmDto) => (
                <ListGroup.Item key={film.filmId}>{film.title}</ListGroup.Item>
              ))}
            {categories.length > 0 &&
              categories.map((category: CategoryDto) => (
                <ListGroup.Item key={category.categoryId}>{category.name}</ListGroup.Item>
              ))}
          </ListGroup>
        </div>
      </div>
    </Container>
  );
};

export default HomeView;
