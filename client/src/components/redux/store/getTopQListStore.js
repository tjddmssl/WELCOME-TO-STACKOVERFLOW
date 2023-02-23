import { configureStore } from '@reduxjs/toolkit';
import getTopQListSlice from '../slice/getTopQListSlice';

const store = configureStore({
  reducer: {
    getTopQList: getTopQListSlice.reducer,
  },
});

export default store;
