package controller.search;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="searchPage", value="/search-page")
public class SearchPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>네이버 검색</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 20px; }");
        out.println(".container { max-width: 800px; margin: 0 auto; }");
        out.println(".search-form { margin-bottom: 20px; }");
        out.println(".search-input { padding: 8px; width: 70%; font-size: 16px; }");
        out.println(".search-button { padding: 8px 16px; font-size: 16px; cursor: pointer; }");
        out.println(".result-item { border-bottom: 1px solid #eee; padding: 15px 0; }");
        out.println(".result-title { font-size: 18px; margin-bottom: 5px; }");
        out.println(".result-title a { color: #1a0dab; text-decoration: none; }");
        out.println(".result-title a:hover { text-decoration: underline; }");
        out.println(".result-description { color: #444; margin-bottom: 5px; }");
        out.println(".result-link { color: #006621; font-size: 13px; margin-bottom: 3px; }");
        out.println(".result-date { color: #777; font-size: 13px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>네이버 뉴스 검색</h1>");
        out.println("<div class='search-form'>");
        out.println("<input type='text' id='keyword' class='search-input' placeholder='검색어를 입력하세요'>");
        out.println("<button id='search-btn' class='search-button'>검색</button>");
        out.println("</div>");
        out.println("<div id='results'></div>");
        out.println("</div>");

        out.println("<script>");
        out.println("document.getElementById('search-btn').addEventListener('click', performSearch);");
        out.println("document.getElementById('keyword').addEventListener('keypress', function(e) {");
        out.println("  if (e.key === 'Enter') performSearch();");
        out.println("});");
        out.println("");
        out.println("function performSearch() {");
        out.println("  const keyword = document.getElementById('keyword').value;");
        out.println("  if (!keyword) return;");
        out.println("");
        out.println("  const resultsDiv = document.getElementById('results');");
        out.println("  resultsDiv.innerHTML = '<p>검색 중...</p>';");
        out.println("");
        out.println("  fetch(`/search?keyword=${encodeURIComponent(keyword)}`).then(response => {");
        out.println("    if (!response.ok) throw new Error('검색 중 오류가 발생했습니다');");
        out.println("    return response.json();");
        out.println("  }).then(data => {");
        out.println("    displayResults(data, keyword);");
        out.println("  }).catch(error => {");
        out.println("    resultsDiv.innerHTML = `<p>오류: ${error.message}</p>`;");
        out.println("  });");
        out.println("}");
        out.println("");
        out.println("function displayResults(results, keyword) {");
        out.println("  const resultsDiv = document.getElementById('results');");
        out.println("  ");
        out.println("  if (!results || results.length === 0) {");
        out.println("    resultsDiv.innerHTML = '<p>검색 결과가 없습니다.</p>';");
        out.println("    return;");
        out.println("  }");
        out.println("");
        out.println("  let html = `<h2>'${keyword}' 검색 결과</h2>`;");
        out.println("  ");
        out.println("  results.forEach(item => {");
        out.println("    html += `");
        out.println("      <div class='result-item'>");
        out.println("        <div class='result-title'><a href='${item.link}' target='_blank'>${item.title}</a></div>");
        out.println("        <div class='result-description'>${item.description}</div>");
        out.println("        <div class='result-link'>${item.link}</div>");
        out.println("        <div class='result-date'>${item.pubDate}</div>");
        out.println("      </div>`;");
        out.println("  });");
        out.println("");
        out.println("  resultsDiv.innerHTML = html;");
        out.println("}");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
    }
}
