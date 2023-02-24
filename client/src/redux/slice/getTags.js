import { createSlice } from '@reduxjs/toolkit';

const getTagsSLice = createSlice({
  name: 'getTags',
  initialState: { tags: [] },
  reducers: {
    get: (state, action) => {
      state.tags = action.payload;
    },
  },
});

export default getTagsSLice;
