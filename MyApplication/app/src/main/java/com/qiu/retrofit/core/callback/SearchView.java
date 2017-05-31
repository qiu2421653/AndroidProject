package com.qiu.retrofit.core.callback;

/**
 * @author Hannes Dorfmann
 */
public interface SearchView  {

  void showLoading();

  void showContent();

  void showError(Throwable t);

  void showLoadingMore(boolean showing);

  void showLoadingMoreError(Throwable t);


  void showSearchNotStarted();
}
