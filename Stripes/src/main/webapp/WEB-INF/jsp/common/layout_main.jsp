<%@page contentType="text/html;charset=ISO-8859-1" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-definition>
  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd">
  <html>
    <head>
      <title>${title}</title><!-- (1) -->
      <link rel="stylesheet" type="text/css"
        href="${contextPath}/css/style.css">
    </head>
    <body>
      <div id="header">
        <span class="title">${title}</span><!-- (3) -->
      </div>
      <div id="body">
        <s:layout-component name="body"/><!-- (4) -->
      </div>


      <!-- View source links just for convenience -->
            <div style="padding-left: 8px">
        Source:
        <s:link beanclass="stripesbook.action.ViewSourceActionBean">
          <s:param name="filename" value="<%= request.getRequestURI() %>"/>
          JSP
        </s:link> |

      </div>

    </body>
  </html>
</s:layout-definition>
