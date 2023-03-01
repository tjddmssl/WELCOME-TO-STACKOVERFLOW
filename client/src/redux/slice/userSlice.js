import { createSlice } from '@reduxjs/toolkit';

// TODO user key 값 전부 넣기
export const userSlice = createSlice({
  name: 'user',
  initialState: { value: { isLogin: false, displayName: '', email: '' } },
  reducers: {
    login: (state, action) => {
      state.value = action.payload;
    },
  },
});

export default userSlice.reducer;
