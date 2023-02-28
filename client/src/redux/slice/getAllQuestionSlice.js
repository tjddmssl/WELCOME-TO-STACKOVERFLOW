import { createSlice } from '@reduxjs/toolkit';

const getAllQuestionSlice = createSlice({
  name: 'getAllQuestion',
  initialState: { response: {} },
  reducers: {
    get: (state, action) => {
      state.response = action.payload;
    },
  },
});

export default getAllQuestionSlice;
