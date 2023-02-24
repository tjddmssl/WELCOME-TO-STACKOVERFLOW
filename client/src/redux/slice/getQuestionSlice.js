import { createSlice } from '@reduxjs/toolkit';

const getQuestionSlice = createSlice({
  name: 'getQuestion',
  initialState: { response: {} },
  reducers: {
    get: (state, action) => {
      state.response = action.payload;
    },
  },
});

export default getQuestionSlice;
