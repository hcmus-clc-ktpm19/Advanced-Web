import axios from "axios";
import {CategoryDto} from "../models/model";

export const CategoryService = {
  getCategories: async (): Promise<CategoryDto[]> => {
    return (
        await axios.get('http://localhost:8080/api/v1/categories')
    ).data;
  },
  getCategoryByName: async (name: string): Promise<CategoryDto> => {
    return (
        await axios.get(`http://localhost:8080/api/v1/categories/${name}`)
    ).data;
  },
  createCategory: async (categoryDto: CategoryDto): Promise<CategoryDto> => {
    return (
        await axios.post('http://localhost:8080/api/v1/categories', categoryDto)
    ).data;
  },
  deleteCategoryById: async (id: number): Promise<void> => {
    await axios.delete(`http://localhost:8080/api/v1/categories/${id}`);
  }
};