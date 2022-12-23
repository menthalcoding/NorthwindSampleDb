using Microsoft.AspNetCore.Mvc.Rendering;
using System;

namespace OnlineCoding.Code.Helper
{
    public static class DbHelper
    {
        public static T ConvertFromDBVal<T>(object obj)
        {
            if (obj == null || obj == DBNull.Value)
            {
                return default(T); // returns the default value for the type
            }
                
            return (T)obj;
        }
    }

    public static class ListExtensions
    {
        public static List<SelectListItem> ToSelectList<T>(this List<T> list, string idPropertyName, string namePropertyName = "Name", string firstItem = "-- Please Select --")
            where T : class, new()
        {
            List<SelectListItem> selectListItems = new List<SelectListItem>();

            if (!string.IsNullOrEmpty(firstItem))
            {
                selectListItems.Add(new SelectListItem
                {
                    Text = firstItem,
                    Value = ""
                });
            }

            list.ForEach(item =>
            {
                selectListItems.Add(new SelectListItem
                {
                    Text = item.GetType().GetProperty(namePropertyName).GetValue(item).ToString(),
                    Value = item.GetType().GetProperty(idPropertyName).GetValue(item).ToString()
                });
            });

            return selectListItems;
        }
    }
}