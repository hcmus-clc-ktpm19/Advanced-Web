import axios from 'axios';

export const AuthService = {
  login: async (username: string, password: string): Promise<any> => {
    try {
      const res = await axios.post<any>('http://localhost:8080/api/v1/auth/login', {
        username,
        password
      });

      localStorage.setItem('token', `${res.data.type} ${res.data.accessToken}`);
      localStorage.setItem('refreshToken', res.data.refreshToken);

      return res.data;
    } catch (e) {
      if (axios.isAxiosError(e)) {
        throw new Error(e.response?.data.message);
      }
    }
  },

  getNewAccessToken: async (): Promise<any> => {
    try {
      const res = await axios.post<any>('http://localhost:8080/api/v1/auth/refreshtoken', {
        refreshToken: localStorage.getItem('refreshToken') || ''
      });

      localStorage.setItem('token', `Bearer ${res.data.accessToken}`);
      localStorage.setItem('refreshToken', res.data.refreshToken);

      return res.data;
    } catch (e) {
      if (axios.isAxiosError(e)) {
        throw new Error(e.response?.data.message);
      }
    }
  }
};