<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
  title="Contact List">
  <s:layout-component name="body">
    <d:table name="${actionBean.contacts}" id="contact" requestURI="" defaultsort="1" >
        <d:column title="Last name" property="lastName" sortable="true" />
        <d:column title="First name" property="firstName" sortable="true" />
        <d:column title="Email" property="email" sortable="true"/>
        <d:column title="Action">
        <s:link beanclass="stripesbook.action.ContactListActionBean"
        event="view" >
        <s:param name="contactId" value="${contact.id}"/>
        View
        </s:link> |
        <s:link beanclass="stripesbook.action.ContactListActionBean"
        event="delete" >
        <s:param name="contactId" value="${contact.id}"/>
        Delete
        </s:link>
        </d:column>
    </d:table>
  </s:layout-component>
</s:layout-render>