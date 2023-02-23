import { createSlice } from '@reduxjs/toolkit';

const getTopQListSlice = createSlice({
  name: 'getTopQList',
  initialState: { content: [] },
  reducers: {
    get: (state, action) => {
      state.content = action.payload;
    },
  },
});

export default getTopQListSlice;
