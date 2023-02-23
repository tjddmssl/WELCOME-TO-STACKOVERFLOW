import { createSlice } from '@reduxjs/toolkit';

const getQViewSlice = createSlice({
  name: 'getQView',
  initialState: { question: [] },
  reducers: {
    get: (state, action) => {
      state.question = action.payload;
    },
  },
});

export default getQViewSlice;
