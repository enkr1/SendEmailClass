/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pang Jing Hui
 */


public class JavaMail {
    public static void main(String[] args) {
        try {
            EmailUtility.sendEmail("jinghuipang99@gmail.com");
        } catch(Exception ex){
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
}
