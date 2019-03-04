package com.weather.zhigao.model

class SearchCityEntity {


    lateinit var HeWeather6: MutableList<HeWeather6Bean>

    class HeWeather6Bean {
        /**
         * basic : [{"cid":"CN101150304","location":"河南","parent_city":"黄南","admin_area":"青海","cnty":"中国","lat":"34.73452377","lon":"101.61187744","tz":"+8.00","type":"city"},{"cid":"CN101080706","location":"河南","parent_city":"鄂尔多斯","admin_area":"内蒙古","cnty":"中国","lat":"37.85810089","lon":"108.73110199","tz":"+8.00","type":"city"},{"cid":"CN101180710","location":"唐河","parent_city":"南阳","admin_area":"河南","cnty":"中国","lat":"32.68789291","lon":"112.83849335","tz":"+8.00","type":"city"},{"cid":"CN101180101","location":"郑州","parent_city":"郑州","admin_area":"河南","cnty":"中国","lat":"34.75797653","lon":"113.6654129","tz":"+8.00","type":"city"},{"cid":"CN101180901","location":"洛阳","parent_city":"洛阳","admin_area":"河南","cnty":"中国","lat":"34.66304016","lon":"112.43447113","tz":"+8.00","type":"city"},{"cid":"CN101181001","location":"商丘","parent_city":"商丘","admin_area":"河南","cnty":"中国","lat":"34.43705368","lon":"115.65049744","tz":"+8.00","type":"city"},{"cid":"CN101180201","location":"安阳","parent_city":"安阳","admin_area":"河南","cnty":"中国","lat":"36.10344315","lon":"114.35248566","tz":"+8.00","type":"city"},{"cid":"CN101181601","location":"驻马店","parent_city":"驻马店","admin_area":"河南","cnty":"中国","lat":"32.98016739","lon":"114.0247345","tz":"+8.00","type":"city"},{"cid":"CN101180301","location":"新乡","parent_city":"新乡","admin_area":"河南","cnty":"中国","lat":"35.19002151","lon":"113.80618286","tz":"+8.00","type":"city"},{"cid":"CN101180701","location":"南阳","parent_city":"南阳","admin_area":"河南","cnty":"中国","lat":"32.99908066","lon":"112.54091644","tz":"+8.00","type":"city"}]
         * status : ok
         */

        var status: String? = null
       lateinit var basic: MutableList<BasicBean>

        class BasicBean {
            /**
             * cid : CN101150304
             * location : 河南
             * parent_city : 黄南
             * admin_area : 青海
             * cnty : 中国
             * lat : 34.73452377
             * lon : 101.61187744
             * tz : +8.00
             * type : city
             */

            var cid: String? = null
            var location: String? = null
            var parent_city: String? = null
            var admin_area: String? = null
            var cnty: String? = null
            var lat: String? = null
            var lon: String? = null
            var tz: String? = null
            var type: String? = null
        }
    }
}
