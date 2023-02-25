import { createSlice } from '@reduxjs/toolkit';

const viewEditSlice = createSlice({
  name: 'viewEdit',
  initialState: { content: '' },
  reducers: {
    edit: (state, action) => {
      state.content = action.payload;
    },
  },
});

export default viewEditSlice;
