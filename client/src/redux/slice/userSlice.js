import { createSlice } from '@reduxjs/toolkit';

// TODO user key 값 전부 넣기
const initialState = {
  value: { isLogin: false, displayName: '', email: '' },
};

export const userSlice = createSlice({
  name: 'user',
  initialState: initialState,
  reducers: {
    login: (state, action) => {
      state.value = action.payload;
    },
  },
});

export default userSlice.reducer;
