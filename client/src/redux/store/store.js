import { configureStore } from '@reduxjs/toolkit';
import getQViewSlice from '../slice/getQView';
import getAnswerSlice from '../slice/getAnswer';
import getTopQListSlice from '../slice/getTopQListSlice';
import getTagsSLice from '../slice/getTags';
import getQuestionSlice from '../slice/getQuestionSlice';
import viewEditSlice from '../slice/viewEditSlice';
const store = configureStore({
  reducer: {
    getTopQList: getTopQListSlice.reducer,
    getQView: getQViewSlice.reducer,
    getAnswer: getAnswerSlice.reducer,
    getTags: getTagsSLice.reducer,
    getQuestion: getQuestionSlice.reducer,
    viewEdit: viewEditSlice.reducer,
  },
});

export default store;
