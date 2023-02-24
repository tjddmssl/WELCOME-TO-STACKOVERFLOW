import { configureStore } from '@reduxjs/toolkit';
import getQuestionSlice from '../slice/getQuestionSlice';
import getTopQListSlice from '../slice/getTopQListSlice';
import viewEditSlice from '../slice/viewEditSlice';

const store = configureStore({
  reducer: {
    getTopQList: getTopQListSlice.reducer,
    viewEdit: viewEditSlice.reducer,
    getQuestion: getQuestionSlice.reducer,
  },
});

export default store;
