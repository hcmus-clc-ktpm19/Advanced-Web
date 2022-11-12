import axios from 'axios';

export const AuthService = {
  login: async (username: string, password: string): Promise<void> => {
    try {
      await axios.post<string>('http://localhost:8080/api/v1/auth/login', {
        username,
        password
      });
    } catch (e) {
      if (axios.isAxiosError(e)) {
        throw new Error(e.response?.data);
      }
    }
  }
};