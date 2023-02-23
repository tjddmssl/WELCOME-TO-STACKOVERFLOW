import { createSlice } from '@reduxjs/toolkit';

const getAnswerSlice = createSlice({
  name: 'getAnswer',
  initialState: { answer: [] },
  reducers: {
    get: (state, action) => {
      state.answer = action.payload;
    },
  },
});

export default getAnswerSlice;
