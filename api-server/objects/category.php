<?php
class Category{
  
    // database connection and table name
    private $conn;
    private $table_name = "categories";
  
    // object properties
    public $id;
    public $name;
    public $description;
  
    public function __construct($db){
        $this->conn = $db;
    }

    // used by select drop-down list
    public function read(){
    
        //select all data
        $query = "SELECT
                    id, name, description
                FROM
                    " . $this->table_name . "
                ORDER BY
                    name";
    
        $stmt = $this->conn->prepare( $query );
        $stmt->execute();
    
        return $stmt;
    }

    // used when filling up the update product form
    function readOne(){
    
        // query to read single record
        $query = "SELECT
                    *
                FROM
                    " . $this->table_name . "
                WHERE
                    id = ?
                LIMIT
                    0,1";
    
        // prepare query statement
        $stmt = $this->conn->prepare( $query );
    
        // bind id of product to be updated
        $stmt->bindParam(1, $this->id);
    
        // execute query
        $stmt->execute();
    
        // get retrieved row
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
    
        // set values to object properties
        $this->name = $row['name'];
        $this->description = $row['description'];

    }

    // update the category
    function update(){
    
        // get existing category  
        $existingCategory = new Category($this->conn);
        $existingCategory->id = $this->id;
        $existingCategory->readOne();

        // update query
        $query = "UPDATE 
                    " . $this->table_name . " 
                    SET 
                        name = :name, 
                        description = :description
                    WHERE 
                        id = :id
                ";

        // prepare query statement
        $stmt = $this->conn->prepare($query);    

        // sanitize & insert existing product info if not provided
        $this->id=htmlspecialchars(strip_tags($this->id)); 
        // $this->name = $this->name == "" ? $existingProduct->name : htmlspecialchars(strip_tags($this->name)); 
        // $this->description = $this->description == "" ? $existingProduct->description : htmlspecialchars(strip_tags($this->description)); 
        $this->name = htmlspecialchars(strip_tags($this->name)); 
        $this->description = htmlspecialchars(strip_tags($this->description)); 

        // bind new values
        $stmt->bindParam(':id', $this->id); 
        $stmt->bindParam(':name', $this->name); 
        $stmt->bindParam(':description', $this->description); 
        
        // execute the query
        if($stmt->execute()){
			return true;
        }
        return false;
    }
}
?>