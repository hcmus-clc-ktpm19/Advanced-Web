import {Button, Container, Table} from "react-bootstrap";
import {CategoryDto} from "../../models/model";
import React, {useEffect, useState} from "react";
import {CategoryService} from "../../services/CategoryService";
import {AxiosError} from "axios";
import {Link} from "react-router-dom";


const HomeView = (): JSX.Element => {
  const [categories, setCategories] = useState<CategoryDto[]>([]);
  useEffect(() => {
    CategoryService.getCategories().then((response) => {
      setCategories(response);
      console.log(response);
    }).catch((error: any | AxiosError) => {
      console.log(error);
    });
  }, []);

  const deleteCategoryHandler = (id: number): void => {
    CategoryService.deleteCategoryById(id).then((response) => {
      console.log(response);
      setCategories(categories.filter((category) => category.categoryId !== id));
    }).catch((error: any | AxiosError) => {
      console.log(error);
    });
  }

  return (
      <Container className="px-4 py-5">
        <h2 className="pb-2 border-bottom fw-bold">Home</h2>
        <Table striped bordered hover>
          <thead>
          <tr>
            <th>Category ID</th>
            <th>Name</th>
            <th>Last Update</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          {
            categories.map(category => {
              return (
                  <tr key={category.categoryId}>
                    <td>{category.categoryId}</td>
                    <td>{category.name}</td>
                    <td>{category.lastUpdate.toString()}</td>
                    <td>
                      <Link to={`/categories/${category.categoryId}`}>
                        <Button variant="primary">Edit</Button>
                      </Link>
                      <div className={"more-space"}/>
                      <Button variant={"danger"}
                              className={"float-end"}
                              onClick={() => deleteCategoryHandler(category.categoryId)}
                      >Delete
                      </Button>
                    </td>
                  </tr>
              )
            })
          }
          </tbody>
        </Table>
      </Container>
  );
}
export default HomeView;