/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vida;

/**
 *
 * @author luis
 */
public class Animal {
    private int tipo_a;
    private int fome;
    
    public Animal(){
        tipo_a = 0;
        fome = -1;
    }
    public Animal(int t){
        tipo_a = t;
        fome = -1;
    }
    public Animal(int t, int f){
        tipo_a = t;
        fome = f;
    }
    public int getTipo(){
        return tipo_a;
    }
    public void setTipo(int t){
        tipo_a = t;
    }
    public int getFome(){
        return fome;
    }
    public void setFome(int f){
        fome = f;
    }
    
    
}
