import { createSlice } from '@reduxjs/toolkit';

const getAllQuesionSlice = createSlice({
  name: 'getAllQuestion',
  initialState: { response: {} },
  reducers: {
    get: (state, action) => {
      state.response = action.payload;
    },
  },
});

export default getAllQuesionSlice;
