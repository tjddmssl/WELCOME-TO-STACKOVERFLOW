import { configureStore } from '@reduxjs/toolkit';
import getQViewSlice from '../slice/getQView';
import getAnswerSlice from '../slice/getAnswer';
import getTopQListSlice from '../slice/getTopQListSlice';

const store = configureStore({
  reducer: {
    getTopQList: getTopQListSlice.reducer,
    getQView: getQViewSlice.reducer,
    getAnswer: getAnswerSlice.reducer,
  },
});

export default store;
