import {Container, ListGroup, ToggleButton, ToggleButtonGroup} from "react-bootstrap";
import {ActorDto, CategoryDto, FilmDto} from "../../models/model";
import React, {useState} from "react";
import {ActorService} from "../../services/ActorServices";
import {CategoryService} from "../../services/CategoryService";
import {FilmService} from "../../services/FilmService";
import {AxiosError} from "axios";


const HomeView = (): JSX.Element => {
  const [actors, setActors] = useState<ActorDto[]>([]);
  const [categories, setCategories] = useState<CategoryDto[]>([]);
  const [films, setFilms] = useState<FilmDto[]>([]);

  const handleOnclick = async (event: React.MouseEvent<HTMLElement>) => {
    const htmlElement = event.target as HTMLElement;
    setActors([]);
    setCategories([]);
    setFilms([]);
    await fetchData(htmlElement.id);
  }

  const fetchData = async (apiName: string): Promise<void> => {
    try {
      switch (apiName) {
        case 'actors':
          setActors(await ActorService.getActors());
          break;
        case 'categories':
          setCategories(await CategoryService.getCategories());
          break;
        case 'films':
          setFilms(await FilmService.getFilms());
          break;
      }
    }catch (e: any | AxiosError){
      console.error(e);
    }
  }

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
}
export default HomeView;