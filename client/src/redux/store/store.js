import { configureStore } from '@reduxjs/toolkit';
import getQView from '../slice/getQView';
import getTopQListSlice from '../slice/getTopQListSlice';

const store = configureStore({
  reducer: {
    getTopQList: getTopQListSlice.reducer,
    getQView: getQView.reducer,
  },
});

export default store;
