package com.ftd.util.dbclient;

import java.util.ArrayList;
import java.util.List;

public class PageQuery {

	public static <T> Result<T> getPageResult(int page, int pageSize,
			List<T> list) {
		int total = list.size();
		List<T> subList = new ArrayList<T>();

		// 页数最小为1页
		if (page < 1) {
			page = 1;
		}

		int begin = (page - 1) * pageSize;
		int end = page * pageSize;

		// 所需的记录不够， 返回空， 总共有10条， 需返回10-20的情况
		if (total <= begin)
			return new Result<T>(total, subList);

		// 所需记录不够， 总共有15条，需返回10-20的情况
		if (total < end)
			end = total;

		return new Result<T>(total, list.subList(begin, end));
	}

	public static class Result<T> {
		public final int total;
		public final List<T> list;

		public Result(int total, List<T> list) {
			this.total = total;
			this.list = list;
		}
	}

}
